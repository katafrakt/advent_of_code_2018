(load-file "../_helpers/aoc.clj")

(defn process-log [current-guard guards-map log]
  (if (empty? log) guards-map
      (let [line (first log)
             guard-match (re-matches #".*Guard #(\d+).*" line)
             time-match (re-matches #"\[[^ ]+ \d+:(\d+).*" line)]
         (if guard-match
           (process-log (nth guard-match 1) guards-map (rest log))
           (let [guard-entry (get guards-map current-guard [])
                 new-entry (conj guard-entry (parse-int (nth time-match 1)))
                 new-map (assoc guards-map current-guard new-entry)]
             (process-log current-guard new-map (rest log))) 
           ))))

(defn log-to-minutes [log]
  (let [pairs (partition 2 log)]
       (doall (mapcat #(range (first %) (second %)) pairs))))

(defn guard-minutes [guard-map]
  (reduce-kv #(assoc %1 %2 (log-to-minutes %3)) {} guard-map))

(defn guard-data [minutes]
  (map #(identity {:id (first %) :minutes (second %) :total_sleep (count (second %))}) (seq minutes)))

(defn get-most-sleeping [data]
  (last (sort-by :total_sleep data)))

(defn most-slept-minute [minutes]
  (first (last (sort-by second (frequencies minutes)))))

(let [input (sort (read-input-lines "input.txt"))
      guard-logs (process-log nil {} input)
      minutes (guard-minutes guard-logs)
      data (guard-data minutes)
      sleeper (get-most-sleeping data)
      most-slept-minute (most-slept-minute (get sleeper :minutes))]
  (println (* (parse-int (get sleeper :id)) most-slept-minute)))