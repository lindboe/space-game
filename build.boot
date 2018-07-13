(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[org.clojure/clojure "1.9.0" :scope "provided"]
                  [adzerk/boot-cljs "2.1.4" :scope "test"]
                  [adzerk/boot-cljs-repl   "0.3.3"]
                  [com.cemerick/piggieback "0.2.1"  :scope "test"]
                  [weasel                  "0.7.0"  :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                  [adzerk/boot-reload "0.5.2" :scope "test"]
                  [adzerk/boot-test "1.2.0" :scope "test"]
                  [pandeiro/boot-http "0.8.3" :scope "test"]
                  [javax.xml.bind/jaxb-api "2.3.0" :scope "test"] ; necessary for Java 9 compatibility
                  ; project deps
                  [org.clojure/clojurescript "1.10.238" :scope "test"]
                  [nightlight "RELEASE"]
                  [play-cljs "1.2.0"]
                  [edna "1.2.0"]])

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[adzerk.boot-test :refer :all]
  '[pandeiro.boot-http :refer [serve]])

(deftask run []
  (set-env! :resource-paths #(conj % "dev-resources"))
  (comp
    (serve :dir "target/public" :port 3000)
    (watch)
    (reload)
    (cljs
      :optimizations :none
      :compiler-options {:asset-path "main.out"})
    (target)))

(deftask cljsrepl []
  (set-env! :resource-paths #(conj % "dev-resources"))
  (comp
    (serve :dir "target/public" :port 3000)
    (watch)
    (reload)
    (cljs-repl)
    (cljs
      :optimizations :none
      :compiler-options {:asset-path "main.out"})
    (target)))

(deftask build []
  (set-env! :resource-paths #(conj % "prod-resources"))
  (comp (cljs :optimizations :advanced) (target)))

(deftask testing [] (merge-env! :source-paths #{"test"}) identity)
