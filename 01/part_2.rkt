(define frequencies '())
(define freq 0)
(define input (file->lines "input.txt"))
(define head (first input))
(define tail (rest input))
(define duplicate-found #f)

(define-syntax-rule (while-loop condition body ...)   
  (let loop ()
    (when condition
      body ...
      (loop))))

(while-loop (not duplicate-found)
  (set! head (first input))
  (set! tail (rest input))
  (set! freq (+ freq (string->number head)))
  (set! input (append tail (list head)))
  (if (member freq frequencies)
    (set! duplicate-found #t)
    (set! frequencies (append frequencies (list freq)))
  )
)
(print freq)
