import { Component, EventEmitter, inject, Output } from '@angular/core';
import { Movie } from "../../models/movie";
import { AsyncPipe } from "@angular/common";
import { MovieService } from "../../movie-service";
import { Observable } from "rxjs";
import { Loading } from "../../shared/loading/loading";

@Component({
  selector: 'app-movie-list',
  imports: [AsyncPipe, Loading],
  templateUrl: './movie-list.html',
})
export class MovieList {
  private movieService = inject(MovieService);
  protected movies$: Observable<Movie[]> = this.movieService.getMovies();
  protected loading$ = this.movieService.loading$;

  @Output() movieClicked = new EventEmitter<Movie>();
}
