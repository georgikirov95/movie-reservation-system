export interface Movie {
  id: number;
  title: string;
  description: string;
  posterUrl: string;
  genre: {
    id: number;
    name: string
  }
}
