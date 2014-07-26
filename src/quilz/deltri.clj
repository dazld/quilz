(ns quilz.deltri
  (:require [quil.core :as q] ))

(def num-points 100)
(def epsilon 1e-3)


(defstruct tri :v1 :v2 :v3)
(defstruct point :x :y :z)

(defn make-point [x y]
  (struct point (rand-int x) (rand-int y)))

(defn points [size x y]
  (take size (repeatedly (partial make-point x y))))

(defn find-largest [vertices]
  (reduce (fn [save current]
            (max save (:x current) (:y current)) 
            ) 0 vertices ))

(defn supertri [vertices]
  (let [M (find-largest vertices)
        M10 (* M 10)
        M-10 (* M -10)]
    (struct tri (struct point M10 0 0)
            (struct point 0 M10 0)
            (struct point M-10 M-10 0)) 
    ))

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

(defn contains-point [t p]
  
  (let [ax (- (:x (:v1 t)) (:x p)) 
        ay (- (:y (:v1 t)) (:y p))
        bx (- (:x (:v2 t)) (:x p))
        by (- (:y (:v2 t)) (:y p))
        cx (- (:x (:v3 t)) (:x p))
        cy (- (:y (:v3 t)) (:y p))
        d-ab (- (* ax by) (* bx ay))
        d-bc (- (* bx cy) (* cx by))
        d-ca (- (* cx ay) (* ax cy))
        a-s (+ (* ax ax) (* ay ay))
        b-s (+ (* bx bx) (* by by))
        c-s (+ (* cx cx) (* cy cy))
        calc (+ (* a-s d-bc) (* b-s d-ca) (* c-s d-ab))]
      (if (> calc 0)
        true
        false)
    ))

(defn vertex-shared [t1 t2]
  (let [compared (for [[k v] t1 [k1 v1] t2] (do (= v v1)))]
    (reduce (fn [save val] (if val (reduced true) false)) compared)))

(defn process-points [pts stri]
  (let [tris (atom [stri])]
    (reduce (fn [save p]
              (let [buffer (atom [])]
                (doseq [t (reverse @tris)] 
                  (if (contains-point t p)
                    t)) 

                )) [] pts )))


(defn deltri [pts]
  (if (< (count pts) 3)
    (throw "too few points"))
  (let [ ]))

(defn draw []
  (q/background 200)
  (q/no-stroke)
  (q/frame-rate 0.5)
  (let [w (q/width)
        h (q/height)
        pts (points 3 w h)
        stri (supertri pts)]
    (doseq [x pts] (draw-point x))
    (process-points pts stri)
    ;(println (contains-point stri (first pts)))
    ;(println (vertex-shared stri stri))
    ))


(q/defsketch thing
  :title "thing"
  :setup setup
  :draw draw
  :size [320 320]
  :renderer "processing.core.PGraphicsRetina2D")

