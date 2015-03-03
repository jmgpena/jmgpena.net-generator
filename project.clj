(defproject jmgpena-net "0.1.0-SNAPSHOT"
  :description "Jorge Pena's personnal website"
  :url "http://jmgpena.net"
  :license {:name "Public Domain"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [stasis "2.2.2"]
                 [ring "1.3.2"]
                 [hiccup "1.0.5"]
                 [me.raynes/cegdown "0.1.1"]
                 [garden "1.2.5"]
                 [org.clojure/tools.nrepl "0.2.7"]]
  :plugins [[lein-ring "0.9.2"]]
  :ring {:handler jmgpena-net.core/app}
  :aliases {"build-site" ["run" "-m" "jmgpena-net.core/export"]})
