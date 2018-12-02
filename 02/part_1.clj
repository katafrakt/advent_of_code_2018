(load-file "../_helpers/aoc.clj")

(defn calc23 [id]
  (let [freqs (frequencies id)]
    (reduce 
      (fn [acc [letter n]] [
        (if (= n 2) 1 (nth acc 0))
        (if (= n 3) 1 (nth acc 1))
      ])
      [0 0]
      freqs)
  ))

(let [input (read-input-lines "input.txt")
  result (reduce 
    (fn [result input] (map + result (calc23 input)))
    [0 0]
    input)]
  (println (reduce * result)))
