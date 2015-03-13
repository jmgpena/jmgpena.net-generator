(ns jmgpena-net.css
  (:require [stasis.core :as stasis]
            [garden.core :refer [css]]
            [clojure.string :as str]))

;; solarized colors
(def colors {
             :base03  "#002b36"
             :base02  "#073642"
             :base2   "#eee8d5"
             :base3   "#fdf6e3"
             :yellow  "#b58900"
             :orange  "#cb4b16"
             :red     "#dc322f"
             :magenta "#d33682"
             :violet  "#6c71c4"
             :blue    "#268bd2"
             :cyan    "#2aa198"
             :green   "#859900"
             })

;; reset (Eric Meyer)
(defn- reset []
  (css
   [:html :body :div :span :applet :object :iframe
    :h1 :h2 :h3 :h4 :h5 :h6 :p :blockquote :pre :a
    :abbr :acronym :address :big :cite :code :del :dfn
    :em :img :ins :kbd :q :s :samp :small :strike :strong
    :sub :sup :tt :var :b :u :i :center :dl :dt :dd :ol :ul
    :li :fieldset :form :label :legend :table :caption :tbody
    :tfoot :thead :tr :th :td :article :aside :canvas :details
    :embed :figure :figcaption :footer :header :hgroup :menu
    :nav :output :ruby :section :summary :time :mark :audio :video
    {:margin "0" :padding "0" :border "0" :font-size "100%"
     :font "inherit" :vertical-align "baseline"}]
   [:article :aside :details :figcaption :figure :footer :header
    :hgroup :menu :nav :section
    {:display "block"}]
   [:body {:line-height "1"}]
   [:ol :ul {:list-style "none"}]
   [:blockquote :q {:quotes "none"}]
   [:blockquote:before :blockquote:after :q:before :q:after
    {:content "''"}]
   [:blockquote:before :blockquote:after :q:before :q:after
    {:content "none"}]
   [:table {:border-collapse "collapse" :border-spacing "0"}]))

;; main css file
(defn main-style []
  (str
   (reset)
   (css
       [:body {:background-color (colors :base3)
               :color (colors :base03)}]
       [:a {:color (colors :blue)}
        [:&:visited {:color (colors :violet)}]]
       [:div#header {:font-family "Acme, sans-serif"
                     :font-size "1.6rem"
                     :font-weight "bold"}])))


