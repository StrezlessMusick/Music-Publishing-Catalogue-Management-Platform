import { Component, OnInit } from '@angular/core';
import {ArtistsService} from "../zshared/services/artists.service";
import {Artist} from "../zshared/interfaces/artist";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-artists',
  templateUrl: './artists.component.html',
  styleUrls: ['./artists.component.css']
})
export class ArtistsComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {}

}
