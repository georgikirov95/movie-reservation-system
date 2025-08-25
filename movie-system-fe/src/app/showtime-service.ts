import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, delay, finalize, Observable, of, tap } from "rxjs";
import { Showtime } from "./models/showtime";

@Injectable({
  providedIn: 'root'
})
export class ShowtimeService {
  private baseUrl = "http://localhost:8080";
  private http = inject(HttpClient);
  private movieShowtimes = new Map<number, Showtime[]>();
  private loading = new BehaviorSubject(false);
  public loading$ = this.loading.asObservable();

  getShowtimeByMovieIdAndId(movieId: number, id: number): Observable<Showtime> {
    this.loading.next(true);

    return this.http.get<Showtime>(`${this.baseUrl}/movies/${movieId}/showtimes/${id}`).pipe(
      delay(1000),
      finalize(() => this.loading.next(false)));
  }

  getShowtimesByMovieId(movieId: number): Observable<Showtime[]> {
    if (this.movieShowtimes.has(movieId)) return of(this.movieShowtimes.get(movieId) || []);

    this.loading.next(true);
    return this.http.get<Showtime[]>(`${this.baseUrl}/movies/${movieId}/showtimes`).pipe(
      delay(1000),
      tap((showtimes) => this.movieShowtimes.set(movieId, showtimes)),
      finalize(() => this.loading.next(false)));
  }
}
