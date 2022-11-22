import {Component, Input, OnInit} from '@angular/core';
import {ArtistsService} from "../../../zshared/services/artists.service";
import {Artist} from "../../../zshared/interfaces/artist";

@Component({
  selector: 'app-artist-list-entry',
  templateUrl: './artist-list-entry.component.html',
  styleUrls: ['./artist-list-entry.component.css']
})
export class ArtistListEntryComponent implements OnInit {
  @Input() artist!: Artist;
  @Input() index!: number;

  constructor(private artistsService: ArtistsService) { }

  ngOnInit(): void {
  }

}
