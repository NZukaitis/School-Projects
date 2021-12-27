#|
Returns the absolute value of a list of integers.
@author Nicholas Zukaitis
@param original_list is a list of integers before the absolute value operation.
@param new_list is a list containing the absolute values of the integers in original_list.
|#
(define (abs_value original_list new_list)
  (cond
    ((null? original_list) new_list)
    ((negative? (car original_list)) (abs_value (cdr original_list) (append new_list (list (* (car original_list) -1)) ) ) )
    (else (abs_value (cdr original_list) (append new_list (list (car original_list)) ) ) )
    )
  )

#|
Test Cases:
(abs_value '(1 2 3 4) '())
(abs_value '(0 -2 -3 -5) '())
(abs_value '(-55 -101 -202 -303) '())
(abs_value '(-311 311 311 -311) '())
(abs_value '(4 5 -1 -3 4 -99) '())
(abs_value '() '())
(abs_value '(-0.5) '())
(abs_value '(0 0) '())
(abs_value '(15 24 3) '())
(abs_value '(50 -69 78) '())

Expected Results
(1 2 3 4)
(0 2 3 5)
(55 101 202 303)
(311 311 311 311)
(4 5 1 3 4 99)
()
(0.5)
(0 0)
(15 24 3)
(50 69 78)
|#