import { Component, OnInit } from '@angular/core';
import {ProjectsService} from "../../zshared/services/projects.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Project} from "../../zshared/interfaces/project";
import {tap} from "rxjs";

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
  project: Project;
  id: number;

  constructor(private projectsService:  ProjectsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        param => {
          this.id = +param['id'];
          this.projectsService.getProject(this.id)
            .pipe(
              tap(project => this.project = project)
            ).subscribe();
        }
      );
  }

  onAddToFavorites() {

  }

  onEditProject() {
    this.router.navigate(
      ['edit'],
      {relativeTo: this.route}
    )
  }

  onDeleteProject() {
    this.projectsService.removeProject(this.id)
      .subscribe();
    this.router.navigate(
      ['../'],
      {relativeTo: this.route}
    );
  }

}
