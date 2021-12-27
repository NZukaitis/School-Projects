#|
Returns a list with all instances of a specific atom removed.
@author Nicholas Zukaitis
@param items is the original list that may have atoms removed from it.
@param element is the specific atom that will be removed from the list items.
|#
(define (delete items element)
  (cond
    ((null? items) '())
    ((equal? (car items) element) (delete (cdr items) element))
    (else (append (list (car items)) (delete (cdr items) element)))
    )
  )
#|
Test Cases:
(delete '() 0)
(delete '(1 2 3 4) 1)
(delete '(1 1 1 1) 1)
(delete '(1 2 1 2) 1)
(delete '(3 4 5 6) 7)
(delete '(-4 -3 2 1) -4)
(delete '(0) 0)
(delete '(.5 .5 0 0 0) .5)
(delete '(15 14 13 12 10) 1)
(delete '(90 80 70 60 50 50) 50)

Expected Results:
()
(2 3 4)
()
(2 2)
(3 4 5 6)
(-3 2 1)
()
(0 0 0)
(15 14 13 12 10)
(90 80 70 60)
|#