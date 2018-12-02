(defn read-input-lines [filename]
  (with-open [file (clojure.java.io/reader filename)]
    (doall (line-seq file))))