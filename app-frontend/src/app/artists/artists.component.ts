import { Component, OnInit } from '@angular/core';
import {ArtistsService} from "../zshared/services/artists.service";
import {Artist} from "../zshared/interfaces/artist";

@Component({
  selector: 'app-artists',
  templateUrl: './artists.component.html',
  styleUrls: ['./artists.component.css']
})
export class ArtistsComponent implements OnInit {
  public artists: Artist[] | undefined;
  public addArtist: Artist | undefined;
  public deleteArtist: Artist | undefined;

  constructor(private artistsService: ArtistsService) { }

  ngOnInit(): void {
  }

}
