import {Component, Input, OnInit} from '@angular/core';
import {Track} from "../../../zshared/interfaces/track";

@Component({
  selector: 'app-track-list-entry',
  templateUrl: './track-list-entry.component.html',
  styleUrls: ['./track-list-entry.component.css']
})
export class TrackListEntryComponent implements OnInit {
  @Input() track: Track;

  constructor() { }

  ngOnInit(): void {
  }

}
