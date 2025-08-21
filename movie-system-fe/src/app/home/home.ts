import { Component, } from '@angular/core';
import { Observable } from "rxjs";
import { Loading } from "../shared/loading/loading";
import { Movie } from "../models/movie";
import { MovieService } from "../movie-service";
import { AsyncPipe } from "@angular/common";
import { MovieList } from "./movie-list/movie-list";

@Component({
  selector: 'app-home',
  imports: [Loading, AsyncPipe, MovieList],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  movieCategories = ['Sci-Fi', 'Crime', 'Action', 'Drama']; // example categories
  movies$: Observable<Movie[]>;

  constructor(private movieService: MovieService) {
    this.movies$ = this.movieService.getMovies();
  }
}
