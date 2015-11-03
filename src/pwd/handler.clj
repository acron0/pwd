(ns pwd.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.java.io :as io]
            [selmer.parser :as parser]))

(defn serve-pwd-page
  [seed]
  (-> "public/index.html"
      (io/resource)
      (io/input-stream)
      (slurp)
      (parser/render {:seed seed})))

(defroutes app-routes
  (GET "/pwd2/:seed" [seed] (serve-pwd-page seed))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
