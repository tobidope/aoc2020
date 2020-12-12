package de.tobiasbell.aoc_2020;

import de.tobiasbell.aoc_2020.util.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day11 {

    public static long solve1(final String puzzle) {
        Grid grid = Grid.parse(puzzle);
        var changed = false;
        do {
            Grid newGrid = mutate(grid);
            changed = !grid.equals(newGrid);
            grid = newGrid;
        } while (changed);

        return grid.occupiedSeats();
    }

    public static Grid mutate(Grid grid) {
        var newGrid = new Grid(grid);
        for (int x = 0; x < grid.rows(); x++) {
            for (int y = 0; y < grid.columns(); y++) {
                Place currentPlace = grid.get(x, y);
                final long seats = grid.countSurroundingOccupied(x, y);
                if (currentPlace == Place.EMPTY_SEAT && seats == 0) {
                    currentPlace = Place.OCCUPIED_SEAT;
                } else if (currentPlace == Place.OCCUPIED_SEAT && seats >= 4) {
                    currentPlace = Place.EMPTY_SEAT;
                }
                newGrid.put(x, y, currentPlace);
            }
        }
        return newGrid;
    }

    public static long solve2(String puzzle) {
        Grid grid = Grid.parse(puzzle);
        var changed = false;
        do {
            Grid newGrid = mutateNew(grid);
            changed = !grid.equals(newGrid);
            grid = newGrid;
        } while (changed);

        return grid.occupiedSeats();

    }

    private static Grid mutateNew(Grid grid) {
        var newGrid = new Grid(grid);
        for (int x = 0; x < grid.rows(); x++) {
            for (int y = 0; y < grid.columns(); y++) {
                Place currentPlace = grid.get(x, y);
                final long seats = grid.seatsInSight(x, y);
                if (currentPlace == Place.EMPTY_SEAT && seats == 0) {
                    currentPlace = Place.OCCUPIED_SEAT;
                } else if (currentPlace == Place.OCCUPIED_SEAT && seats >= 5) {
                    currentPlace = Place.EMPTY_SEAT;
                }
                newGrid.put(x, y, currentPlace);
            }
        }
        return newGrid;

    }

    public enum Place {
        FLOOR('.'),
        EMPTY_SEAT('L'),
        OCCUPIED_SEAT('#');

        private final char place;

        Place(char c) {
            this.place = c;
        }

        public static Place of(final int c) {
            return Arrays.stream(values())
                    .filter(p -> p.place == c)
                    .findFirst()
                    .orElseThrow();
        }

        public static List<Place> row(final String row) {
            return row.trim().chars()
                    .mapToObj(Place::of)
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return String.valueOf(place);
        }
    }

    public static class Grid {
        private final List<List<Place>> grid;

        public Grid(List<List<Place>> grid) {
            this.grid = grid;
        }

        public Grid(final Grid g) {
            this.grid = new ArrayList<>(g.grid.size());
            for (var p : g.grid) {
                this.grid.add(new ArrayList<>(p));
            }
        }

        public static Grid parse(final String puzzle) {
            return new Grid(Input.lines(puzzle)
                    .map(Place::row)
                    .collect(Collectors.toList()));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Grid grid1 = (Grid) o;
            return Objects.equals(grid, grid1.grid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(grid);
        }

        public int rows() {
            return grid.size();
        }

        public int columns() {
            return grid.get(0).size();
        }

        public long occupiedSeats() {
            return grid.stream()
                    .flatMap(List::stream)
                    .filter(p -> p == Place.OCCUPIED_SEAT)
                    .count();
        }

        public int countSurroundingOccupied(int x, int y) {
            var count = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    var currentX = x + i;
                    var currentY = y + j;
                    if (this.get(currentX, currentY) == Place.OCCUPIED_SEAT) {
                        count++;
                    }
                }
            }
            return count;
        }

        public void put(int x, int y, Place p) {
            grid.get(x).set(y, p);
        }

        public Place get(int x, int y) {
            try {
                return grid.get(x).get(y);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                return Place.FLOOR;
            }
        }

        @Override
        public String toString() {
            return grid.stream()
                    .map(l -> l.stream().map(Objects::toString).collect(Collectors.joining("")))
                    .collect(Collectors.joining("\n"));
        }

        public int seatsInSight(int x, int y) {
            var count = 0;
            // Up
            for (int i = x - 1; i >= 0; i--) {
                final Place place = get(i, y);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            // Down
            for (int i = x + 1; i < rows(); i++) {
                final Place place = get(i, y);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }

            }
            // Left
            for (int i = y - 1; i >= 0; i--) {
                final Place place = get(x, i);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            // Right
            for (int i = y + 1; i < columns(); i++) {
                final Place place = get(x, i);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            // Up right
            for (int i = 1; x - i >= 0 && y + i < columns(); i++) {
                final Place place = get(x - i, y + i);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            // Up left
            for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
                final Place place = get(x - i, y - i);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            // Down right
            for (int i = 1; x + i < rows() && y + i < columns(); i++) {
                final Place place = get(x + i, y + i);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            // Down left
            for (int i = 1; x + i < rows() && y - i >= 0; i++) {
                final Place place = get(x + i, y - i);
                if (place == Place.OCCUPIED_SEAT) {
                    count++;
                    break;
                }
                if (place == Place.EMPTY_SEAT) {
                    break;
                }
            }
            return count;
        }
    }

}
