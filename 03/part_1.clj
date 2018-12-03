(load-file "../_helpers/aoc.clj")

(defn parse-int [s]
  (Integer. s))

(defn get-points [line]
  (let [matches (re-matches #"#\d+ @ (\d+),(\d+): (\d+)x(\d+)" line)
        offset-x (parse-int (nth matches 1))
        offset-y (parse-int (nth matches 2))
        width (parse-int (nth matches 3))
        height (parse-int (nth matches 4))]
       (for [x (range offset-x (+ offset-x width)) y (range offset-y (+ offset-y height))]
            [x y])))

(let [input (read-input-lines "input.txt")
      points (mapcat (fn [line] (get-points line)) input)
      freqs (frequencies points)
      overlapping (count (filter (fn [[_point, count]] (> count 1)) freqs))]
  (println overlapping))