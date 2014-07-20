(ns quilz.spots
  (:require [quil.core :as q] ))

(def num-points 100)

(defn setup []
  (q/no-stroke)
  (q/hint :enable-retina-pixels)
  (q/smooth)
  (q/frame-rate 1)
  (q/background 200))

(defn make-point [w h]
  [(q/random w) (q/random h)])

(defn points [num x y]
  (take num (repeatedly (partial make-point x y))))

(defn draw-point [x y]
  (q/fill 28 60)
  (let [s (+ 10 (rand-int 90))]
    (q/ellipse x y s s)))

(defn draw-line [x y]
  (q/stroke 140)
  (q/line x y))

(defn draw []
  (q/background 200)
  (q/no-stroke)
  (q/frame-rate 1)
  (let [w (q/width)
        h (q/height)
        pts (points 3 w h)
        tsp (cons (last pts) (pop (vec pts)))]
    (doseq [x pts] (apply draw-point x))
    (let [coords (map (fn [l1 l2] [l1 l2]) pts tsp)]
      (doseq [x coords] (apply draw-line x)))))

(q/defsketch thing
  :title "thing"
  :setup setup
  :draw draw
  :size [1024 768]
  :renderer "processing.core.PGraphicsRetina2D")
