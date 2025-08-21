import { Component, Input } from '@angular/core';
import { Movie } from "../../models/movie";

@Component({
  selector: 'app-movie-list',
  imports: [],
  templateUrl: './movie-list.html',
})
export class MovieList {
  @Input({ required: true }) movies!: Movie[];
}
