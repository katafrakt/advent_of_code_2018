(defn read-input-lines [filename]
  (with-open [file (clojure.java.io/reader filename)]
    (doall (line-seq file))))

(defn parse-int [s]
  (Integer. s))