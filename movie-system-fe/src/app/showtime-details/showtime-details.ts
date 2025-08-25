import { AsyncPipe, DatePipe } from "@angular/common";
import { Component, inject } from '@angular/core';
import { ShowtimeService } from "../showtime-service";
import { Observable } from "rxjs";
import { Showtime } from "../models/showtime";
import { Loading } from "../shared/loading/loading";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-showtime-details',
  imports: [DatePipe, AsyncPipe, Loading],
  templateUrl: './showtime-details.html',
})
export class ShowtimeDetails {
  private showtimeService = inject(ShowtimeService);
  private activatedRoute = inject(ActivatedRoute)
  protected loading$ = this.showtimeService.loading$;
  protected showtime$: Observable<Showtime | null>;

  constructor() {
    const { movieId, id } = this.activatedRoute.snapshot.params;
    this.showtime$ = this.showtimeService.getShowtimeByMovieIdAndId(movieId, id)
  }
}
