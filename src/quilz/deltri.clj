(ns quilz.deltri
  (:require [quil.core :as q] ))

(def num-points 100)

(defstruct tri :v1 :v2 :v3)
(defstruct point :x :y :z)

(defn make-point [x y]
  (struct point (rand-int x) (rand-int y)))

(defn points [size x y]
  (take size (repeatedly (partial make-point x y))))

(defn supertri [vertices]
  (reduce (fn [save current]
            
            ) vertices ))

(defn setup []
  (q/no-stroke)
  (q/hint :enable-retina-pixels)
  (q/smooth)
  (q/frame-rate 1)
  (q/background 200))

(defn draw-point [{:keys [x y z]}]
  (q/fill 28)
  (let [s 3] 
    (q/ellipse x y s s)))

(defn draw-line [x y]
  (q/stroke 140)
  (q/line x y))

(defn distance [[ax ay :as a] [bx by :as b]]
  (q/sqrt (+ 
            (q/sq (- bx ax)) 
            (q/sq (- by ay)))))

(defn draw []
  (q/background 200)
  (q/no-stroke)
  (q/frame-rate 0.5)
  (let [w (q/width)
        h (q/height)
        pts (points 6 w h)]
    (doseq [x pts] (draw-point x))
    ))


(q/defsketch thing
  :title "thing"
  :setup setup
  :draw draw
  :size [320 320]
  :renderer "processing.core.PGraphicsRetina2D")

