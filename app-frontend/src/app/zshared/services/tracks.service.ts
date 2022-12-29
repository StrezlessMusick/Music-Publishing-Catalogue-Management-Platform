import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Track} from "../interfaces/track";

@Injectable({
  providedIn: 'root'
})
export class TracksService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getTracks(): Observable<Track[]> {
    return this.http
      .get<Track[]>(`${this.apiServerUrl}/v1/tracks/all`);
  }

  public getTrack(trackId: Track): Observable<Track> {
    return this.http
      .get<Track>(`${this.apiServerUrl}/v1/tracks/find${trackId}`);
  }

  public addTrack(newTrack: Track): Observable<Track> {
    return this.http
      .post<Track>(`${this.apiServerUrl}/v1/tracks/add`, newTrack);
  }

  public updateTrack(track: Track): Observable<Track> {
    return this.http
      .put<Track>(`${this.apiServerUrl}/v1/tracks/update`, track);
  }

  public removeTrack(trackId: number): Observable<void> {
    return this.http
      .delete<void>(`${this.apiServerUrl}/v1/tracks/delete${trackId}`);
  }
}
