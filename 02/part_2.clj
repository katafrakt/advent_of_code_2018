(load-file "../_helpers/aoc.clj")

(defn different-letters [str1 str2]
  (let [letters-at-positions (map-indexed (fn [idx i] [i (nth str2 idx)]) str1)
    difference-values (map (fn [[ch1 ch2]] (if (= ch1 ch2) 0 1)) letters-at-positions)]
    (reduce + difference-values)))

(defn search-for-diff-one [candidate coll]
  (let [new-first (first coll)
    new-rest (rest coll)]
    (if (= 1 (different-letters candidate new-first))
      [candidate new-first]
      (search-for-diff-one new-first new-rest))))

(defn find-common-part [str1 str2]
  (let [letters-at-positions (map-indexed (fn [idx i] [i (nth str2 idx)]) str1)
    common-letters (map (fn [[ch1 ch2]] (if (= ch1 ch2) ch1 nil)) letters-at-positions)]
    (clojure.string/join common-letters)))

(let [input (read-input-lines "input.txt")
  sorted (sort input)
  first (first sorted)
  rest (rest sorted)
  [first second] (search-for-diff-one first rest)
  common-part (find-common-part first second)]
  (println common-part))
