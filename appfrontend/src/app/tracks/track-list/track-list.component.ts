import { Component, OnInit } from '@angular/core';
import {TracksService} from "../../zshared/services/tracks.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Track} from "../../zshared/interfaces/track";
import {tap} from "rxjs";

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
      .pipe(tap(
          (tracks: Track[]) => {
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
