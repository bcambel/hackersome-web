(defproject hsweb "0.1.0-SNAPSHOT"
  :description "Hackersome Front End"
  :url "http://hackersome.com"
  :license {:name "MIT Licence"
            :url "http://raw.github.com/bcambel/hackersome/blob/master/LICENCE"}

  :source-paths ["src/clj" "src/cljs"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371" :scope "provided"]
                 [ring "1.3.1"]
                 [compojure "1.2.0"]
                 [enlive "1.1.5"]
                 [om "0.7.3"]
                 [figwheel "0.1.4-SNAPSHOT"]
                 [environ "1.0.0"]
                 [com.cemerick/piggieback "0.1.3"]
                 [com.cognitect/transit-cljs "0.8.192"]
                 [prismatic/om-tools "0.3.6"]
                  [prismatic/dommy "1.0.0"]
                  [prismatic/plumbing "0.3.5"]
                  [cljs-http "0.1.20"]
                 [weasel "0.4.0-SNAPSHOT"]
                 [leiningen "2.5.0"]]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-environ "1.0.0"]]

  :min-lein-version "2.5.0"

  :uberjar-name "hsweb.jar"

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :preamble      ["react/react.min.js"]
                                        :externs       ["react/externs/react.js"]
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns hsweb.server
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :plugins [[lein-figwheel "0.1.4-SNAPSHOT"]]

                   :figwheel {:http-server-root "public"
                              :port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:is-dev true}

                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]}}}}

             :uberjar {:hooks [leiningen.cljsbuild]
                       :env {:production true}
                       :omit-source true
                       :aot :all
                       :cljsbuild {:builds {:app
                                            {:source-paths ["env/prod/cljs"]
                                             :compiler
                                             {:optimizations :advanced
                                              :pretty-print false}}}}}})
