(ns jmgpena-net.core
  (:require [stasis.core :as stasis]
            [hiccup.util :as hu]
            [hiccup.page :refer [html5]]
            [me.raynes.cegdown :as md]
            [garden.core :refer [css]]
            [jmgpena-net.web :as web]))

(def target-dir "export")

(def app (stasis/serve-pages web/pages))

(defn export []
  (stasis/empty-directory! target-dir)
  (stasis/export-pages web/pages target-dir))
