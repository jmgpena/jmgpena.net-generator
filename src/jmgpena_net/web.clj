(ns jmgpena-net.web
  (:require [stasis.core :as stasis]))

(defn get-pages []
  (stasis/slurp-directory "resources/public" #".*\.(html|css|js)$"))
