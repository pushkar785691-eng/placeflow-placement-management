export type Role='STUDENT'|'ADMIN';
export type Status='APPLIED'|'UNDER_REVIEW'|'SHORTLISTED'|'INTERVIEW'|'OFFERED'|'REJECTED'|'WITHDRAWN';
export interface User{id:number;name:string;email:string;role:Role;course?:string;graduationYear?:number;resumeUrl?:string}
export interface Job{id:number;title:string;company:string;location:string;description:string;employmentType:string;salary?:number;minimumCgpa?:number;deadline?:string;active:boolean;createdAt:string}
export interface Application{id:number;jobId:number;jobTitle:string;company:string;studentId:number;studentName:string;studentEmail:string;status:Status;coverLetter?:string;appliedAt:string;updatedAt:string}
export interface Page<T>{content:T[];totalElements:number;totalPages:number;number:number}
