(ns space-game.grid-state
  "A namespace for experiementing with managing the state of the grid, which
  can be used to position elements to render.")

(def test-grid-row
  (vec (take 5 (repeat 0))))

(def test-grid
  "A list of five lists with five zeroes each."
  (vec (take 5 (repeat test-grid-row))))

(defn place-in-grid
  "Takes a grid and a character, where characters are a map with a name and
  initial starting x,y coordinates (top left is '(0 0)). Replaces the 0 in
  the grid with the name of the 'character'.

  (place-in-grid grid {:name :badguy :coordinates [0 1]})"
  [grid character]
  (let [character-name (:name character)
        [x y] (:coordinates character)]
    (assoc-in grid [y x] character-name)))

;; would a flat array be easier? then can use x*width + column to find the index to update
;; I'm not convinced it makes too much of a difference, might be interesting to benchmark tho

(defn initialize-grid-state
  "Initializes a grid. Can provide a list of 'characters', where characters are
  a map with a name and initial starting x,y coordinates (top left is '(0 0)).
  Replaces the 0 in the grid with the name of the 'character'.

  (initialize-grid-state [{:name :badguy :coordinates [0 1]}]"
  [character-list]

  )
