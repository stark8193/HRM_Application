import { ILeaveRequest } from 'app/shared/model/leave-request.model';

export interface ILeaveType {
  id?: number;
  leaveTypeName?: string;
  description?: string | null;
  leaveRequests?: ILeaveRequest[] | null;
}

export const defaultValue: Readonly<ILeaveType> = {};
