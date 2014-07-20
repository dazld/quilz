(ns quilz.matrices)

(def nums (for [a (range 3) b (range 3)] [a b]))
(def nums3x3 (for [a (range 3) b (range 3)] [a b]))
(def nums128x128 (for [a (range 1024) b (range 1024)] [b a]))
(def nums128x128v (vec (for [a (range 1024) b (range 1024)] [b a])))


(vec nums)


; returns a function that will always work on same
; data set
(defn getValGetter [nums dimx dimy]
  ; nums is a linear sequence
  ; representing the elemens in a two dimensional grid
  ; of size dimx and dimy
  (fn [ax ay]
    (if (>= ax dimx)
      (throw)
    (nth nums
         (+ ax (* ay dimy)))
    )))

(def get3x3 (getValGetter nums 3 3))
(def get128x128 (getValGetter nums128x128 1024 1024))
(count nums)
(count nums128x128)

(get128x128 127 489)


(get3x3 2 1)

(getVal nums 1 2)
