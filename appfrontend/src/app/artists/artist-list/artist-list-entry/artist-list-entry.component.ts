import {Component, Input, OnInit} from '@angular/core';
import {ArtistsService} from "../../../zshared/services/artists.service";
import {Artist} from "../../../zshared/interfaces/artist";
import {empty} from "rxjs";

@Component({
  selector: 'app-artist-list-entry',
  templateUrl: './artist-list-entry.component.html',
  styleUrls: ['./artist-list-entry.component.css']
})
export class ArtistListEntryComponent implements OnInit {
  @Input() artist: Artist;

  constructor(private artistsService: ArtistsService) { }

  ngOnInit(): void {
  }



}
