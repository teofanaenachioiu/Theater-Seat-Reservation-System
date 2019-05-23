import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
  selector: 'ngbd-modal-add',
  template: `
    <div class="modal-header">
      <h4 class="modal-title" id="modal-title">Add Show</h4>
      <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <div class="form-group">
        <label for="formGroupExampleInput">Name</label>
        <input [(ngModel)]="nameShow" type="text" class="form-control" id="formGroupExampleInput" placeholder="show name...">
      </div>
      <div class="form-group">
        <label >Description</label>
        <textarea rows="7" [(ngModel)]="descriptionShow" type="text" class="form-control" placeholder="description...">
        </textarea>
      </div>
      <strong>{{error}}</strong>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
      <button type="button" class="btn btn-primary" (click)="saveData()">Ok</button>
    </div>
  `
})

export class NgbdModalAddContent {
  @Input() error = '';
  @Input() nameShow;
  @Input() descriptionShow;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  constructor(public modal: NgbActiveModal) {
  }

  saveData() {
    if (this.nameShow !== undefined && this.nameShow !== '' && this.descriptionShow !== undefined && this.descriptionShow !== '') {
      this.passEntry.emit({namee: this.nameShow, descriptionn: this.descriptionShow});
      this.modal.close();
    } else {
      this.error = 'Name and description fields are required';
    }
  }
}
