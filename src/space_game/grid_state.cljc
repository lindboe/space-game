(ns space-game.grid-state
  "A namespace for experiementing with managing the state of the grid, which
  can be used to position elements to render.")

(def test-grid-row
  (take 5 (repeat 0)))

(def test-grid
  "A list of five lists with five zeroes each."
  (take 5 (repeat test-grid-row)))
