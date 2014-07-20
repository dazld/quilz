(ns quilz.squares (:use quil.core))

(defn setup []
  (frame-rate 1)
  (smooth))

(defn make-square [x y size]
  (stroke-weight 1)

  (no-stroke)
  (let [a (rand-int 255)]
    (stroke a)
    (fill a a a (+ 128 (rand-int 128))))
  (if (and (> (rand) 0.5) (> size 10))
    (let [steps 2 half-size (int (/ size steps))]
      (doseq [ax (map int (range x (+ x size) (/ size steps)))
              ay (map int (range y (+ y size) (/ size steps)))]
        (make-square ax ay half-size)))
    (rect x y size size)))

(defn draw []
  (frame-rate 0.1)
  (background 0)
  (let [size 128 height 640 width 640]
    (doseq [x (range 0 height size) y (range 0 width size)]
      (make-square x y size))))

(defsketch thing
  :title "thing"
  :setup setup
  :draw draw
  :size [640 640]
  :renderer :p2d)
