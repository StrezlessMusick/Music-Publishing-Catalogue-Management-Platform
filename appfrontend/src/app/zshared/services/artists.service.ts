import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TracksService} from "./tracks.service";
import {Artist} from "../interfaces/artist";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ArtistsService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private tracksService: TracksService) {}

  public getArtists(): Observable<Artist[]> {
    return this.http
      .get<Artist[]>(`${this.apiServerUrl}/v1/artists/all`);
  }

  public getArtist(artistId: number): Observable<Artist> {
    return this.http
      .get<Artist>(`${this.apiServerUrl}/v1/artists/find${artistId}`);
  }

  public addArtist(newArtist: Artist): Observable<Artist> {
    console.log(newArtist);
    return this.http
      .post<Artist>(`${this.apiServerUrl}/v1/artists/add`, newArtist);
  }

  public updateArtist(artist: Artist): Observable<Artist> {
    return this.http
      .put<Artist>(`${this.apiServerUrl}/v1/artists/update`, artist);
  }

  public removeArtist(artistId: number): Observable<void> {
    return this.http
      .delete<void>(`${this.apiServerUrl}/v1/artists/delete${artistId}`);
  }
}
