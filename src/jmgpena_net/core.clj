(ns jmgpena-net.core
  (:require [stasis.core :as stasis]
            [hiccup.page :refer [html5]]))

(def target-dir "export")

(def index-page
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "jmgpena.net"]]
   [:body
    [:h1 "jmgpena.net"]]))

(def pages {"/index.html" index-page})

(def app (stasis/serve-pages pages))

(defn export []
  (stasis/empty-directory! target-dir)
  (stasis/export-pages pages target-dir))
