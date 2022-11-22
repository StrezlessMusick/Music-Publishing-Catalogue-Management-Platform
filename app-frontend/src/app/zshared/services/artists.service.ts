import { Injectable } from '@angular/core';
import {TracksService} from "./tracks.service";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Artist} from "../interfaces/artist";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ArtistsService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient,
              private tracksService: TracksService) { }

  public getArtists(): Observable<Artist[]> {
    return this.http.get<Artist[]>(`${this.apiServerUrl}/v1/artists/`);
  }
}
