(ns jmgpena-net.web
  (:require [stasis.core :as stasis]
            [hiccup.util :as hu]
            [hiccup.page :refer [html5]]
            [me.raynes.cegdown :as md]
            [garden.core :refer [css]]))

(defn get-md-pages []
  (stasis/slurp-directory "resources/pages" #".*\.md$"))

;; main html template
(defn main-tpl [content]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "jmgpena.net"]
    [:link {:href "http://fonts.googleapis.com/css?family=Acme"
            :rel "stylesheet" :type "text/css"}]
    [:link {:href "main.css" :media "all" :rel "stylesheet" :type "text/css"}]]
   [:body
    [:div#header "jmgpena.net"]
    content]))

;;(def pages (md/to-html (slurp "resources/about.md")))
(defn get-html-pages [pages]
  (let [content (->> pages
                     (vals)
                     (map md/to-html)
                     (map main-tpl))
        urls (->> pages
                  (keys)
                  (map #(clojure.string/replace % #"\.md" ".html")))]
    (zipmap urls content)))

;; main css file
(def main-css
  (css
   [:body {:background-color "#eee"
           :color "#222"}]
   [:div#header {:font-family "Acme, sans-serif"
                 :font-size "1.6rem"
                 :font-weight "bold"}]))

(def pages (assoc (get-html-pages (get-md-pages)) "/main.css" main-css))
