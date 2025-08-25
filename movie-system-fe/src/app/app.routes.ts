import { Routes } from '@angular/router';
import { Home } from "./home/home";
import { ShowtimeDetails } from "./showtime-details/showtime-details";

export const routes: Routes = [
  {
    path: '', component: Home
  },
  {
    path: 'movies/:movieId/showtimes/:id', component: ShowtimeDetails,
  }
];
