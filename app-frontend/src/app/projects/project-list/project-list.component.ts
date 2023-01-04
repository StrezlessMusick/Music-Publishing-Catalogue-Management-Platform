import { Component, OnInit } from '@angular/core';
import {Project} from "../../zshared/interfaces/project";
import {ProjectsService} from "../../zshared/services/projects.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  projects: Project[];

  constructor(private projectsService: ProjectsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
  }

  onNewProject() {

  }
}
