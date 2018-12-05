(load-file "../_helpers/aoc.clj")

(defn abs [n] (max n (- n)))

(defn find-opposite-index [idx [head & tail]]
  (if (= (abs (- (first head) (second head))) 32)
    idx
    (if (empty? tail) nil (recur (inc idx) tail))))

(defn splice [idx col]
  (let [length (count col)
        before (subvec col 0 idx)
        after (if (< idx (- length 2))
                (subvec col (+ 2 idx) length)
                [])]
    (concat before after)))

(defn remove-first-opposite [input]
  (let [pairs (partition 2 1 input)
        index-of-opposite (if (empty? pairs) nil (find-opposite-index 0 pairs))]
    (if index-of-opposite
      (splice index-of-opposite (vec input))
      input)))

(defn remove-opposites [input]
  (loop [text input]
    (let [reduced (remove-first-opposite text)]
      (if (= text reduced)
        reduced
        (recur reduced)))))

(let [input (first (read-input-lines "input.txt"))
      ascii-input (map int input)
      parts (partition 1000 ascii-input)
      partly-reduced (flatten (map #(remove-opposites %) parts))
      reduced (remove-opposites partly-reduced)]
     (println (count reduced)))