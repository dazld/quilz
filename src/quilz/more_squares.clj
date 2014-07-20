(ns quilz.more-squares (:use quil.core))

(defn setup []
  (frame-rate 1)
  (smooth))

(defn ri []
  (rand-int 255))

(defn make-square [x y size]
  (stroke-weight 1)

  (no-stroke)
  (let [a (rand-int 255)]
    ;(stroke a)
    (apply fill [(ri) (ri) (ri) 132]))
  (if (and (> (rand) 0.3) (> size 2))
    (let [steps 3 half-size (int (/ size steps))]
      (doseq [ax (map int (range x (+ x size) (/ size steps)))
              ay (map int (range y (+ y size) (/ size steps)))]
        (make-square ax ay half-size)))
    (rect x y size size)))

(defn draw []
  (frame-rate 0.5)
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
