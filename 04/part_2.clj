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

(defn guard-data [input]
  (let [id (first input)
        minutes (second input)
        freqs (frequencies minutes)
        most-slept-minute (last (sort-by second freqs))
        times-slept (second most-slept-minute)
        minute-slept (first most-slept-minute)]
    {:id id :minute minute-slept :times times-slept}))

(defn get-guard-data [minutes]
  (map #(guard-data %) (seq minutes)))

(defn get-most-sleeping [data]
  (last (sort-by :times data)))

(let [input (sort (read-input-lines "input.txt"))
      guard-logs (process-log nil {} input)
      minutes (guard-minutes guard-logs)
      data (get-guard-data minutes)
      sleeper (get-most-sleeping data)]
    (println (* (parse-int (get sleeper :id)) (get sleeper :minute))))