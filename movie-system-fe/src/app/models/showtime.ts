import { Movie } from "./movie";
import { Seat } from "./seat";

export interface Showtime {
  id: number;
  location: string;
  startTime: Date;
  endTime: Date;
  movie: Movie;
  seats: Seat[];
}