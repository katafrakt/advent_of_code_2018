(print
  (foldl 
    (lambda (e acc) (+ acc (string->number e)))
    0
    (file->lines "input.txt")))
