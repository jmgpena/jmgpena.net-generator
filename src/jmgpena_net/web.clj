(ns jmgpena-net.web
  (:require [stasis.core :as stasis]
            [hiccup.util :as hu]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html5]]
            [me.raynes.cegdown :as md]
            [clojure.string :as str]
            [jmgpena-net.css :as css]))

(defn md-pages []
  (stasis/slurp-directory "resources/pages" #".*\.md$"))

(defn generate-urls [pages]
  (->> pages
       (keys)
       (map #(str/replace % #"\.md" ".html"))))

(defn linkify [url]
  (-> url
      (str/replace #"\.html" "")
      (str/replace #"/" "")
      str/capitalize))

(defn- remove-meta [page]
  (str/replace page #"(?is)^---.*?---" ""))

(defn- extract-meta-block [page]
  (->> page (re-seq #"(?is)^---(.*?)---") first second))

(defn- extract-meta-value [meta value]
  (->> meta (re-seq (re-pattern (str value "\\s*:\\s*(.*)"))) first second))

(defn- extract-title [meta]
  (->> meta (re-seq #"title\s*:\s*(.*)") first second))

(defn link-from-url [[url link]]
  (html [:li
         [:a {:href (str/replace url #"\.md" ".html")}
          link]]))

(defn generate-link-from-page [page]
  (let [meta-section (extract-meta-block (val page))]
    (when (and (seq meta-section) (= (extract-meta-value meta-section "link") "yes"))
      (vector (key page) (extract-title meta-section)))))

(defn generate-menu [pages]
  (let [link-pages (doall (remove nil? (map generate-link-from-page pages)))]
    (html [:ul
           (map link-from-url link-pages)])))

;; main html template
(defn main-tpl [menu content]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "jmgpena.net"]
    [:link {:href "http://fonts.googleapis.com/css?family=Acme"
            :rel "stylesheet" :type "text/css"}]
    [:link {:href "main.css" :media "all" :rel "stylesheet" :type "text/css"}]]
   [:body
    [:div#header [:a {:href "/"} "jmgpena.net"]]
    [:div#menu menu]
    [:div#content content]]))

(defn convert-md [md]
  (md/to-html md [:definitions :tables]))

;;(def pages (md/to-html (slurp "resources/about.md")))
(defn get-html-pages [pages]
  (let [urls (generate-urls pages)
        menu (generate-menu pages)
        content (->> pages
                     (vals)
                     (map remove-meta)
                     (map convert-md)
                     (map (partial main-tpl menu)))]
    (zipmap urls content)))

(defn pages [] (assoc (get-html-pages (md-pages)) "/main.css" (css/main-style)))
