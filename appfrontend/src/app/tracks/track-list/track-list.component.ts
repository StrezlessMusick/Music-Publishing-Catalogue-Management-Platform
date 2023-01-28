import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {tap} from "rxjs";
import {TracksService} from "../../zshared/services/tracks.service";
import {Track} from "../../zshared/interfaces/track";

@Component({
  selector: 'app-track-list',
  templateUrl: './track-list.component.html',
  styleUrls: ['./track-list.component.css']
})
export class TrackListComponent implements OnInit {
  tracks: Track[];

  constructor(private tracksService: TracksService,
              private route: ActivatedRoute,
              private router: Router) {
    this.onGetTracks();
  }

  ngOnInit(): void {}

  onGetTracks() {
    this.tracksService.getTracks()
      .pipe(tap(tracks => {
            console.log(tracks);
            this.tracks = tracks;
          })).subscribe();
  }

  onNewTrack() {
    this.router.navigate(
      ['new'],
      {relativeTo: this.route}
    )
  }
}
