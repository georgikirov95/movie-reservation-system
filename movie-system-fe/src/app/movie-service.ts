import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, delay, finalize, Observable, of, tap } from "rxjs";
import { Movie } from "./models/movie";

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private baseUrl = "http://localhost:8080";
  private http = inject(HttpClient);
  private movies?: Movie[];
  private loading = new BehaviorSubject(false);
  public loading$ = this.loading.asObservable();

  getMovies(): Observable<Movie[]> {
    if (this.movies !== undefined) return of(this.movies);

    this.loading.next(true);
    return this.http.get<Movie[]>(`${this.baseUrl}/movies`).pipe(
      delay(1000),
      tap((movies) => this.movies = movies),
      finalize(() => this.loading.next(false)));
  }
}
