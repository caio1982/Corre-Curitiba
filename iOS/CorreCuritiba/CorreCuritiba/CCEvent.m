//
//  CCEvent.m
//  CorreCuritiba
//
//  Created by cits on 16/10/12.
//  Copyright (c) 2012 Caio Begotti. All rights reserved.
//

#import "CCEvent.h"

#import "CCData.h"

@implementation CCEvent

@synthesize local;
@synthesize name;
@synthesize date;
@synthesize enrollment;
@synthesize applications;
@synthesize distance;
@synthesize description;
@synthesize link;

-(id)initWithSection:(NSInteger)section andRow:(NSInteger)row
{
    NSString *sectionString = [[CCDataSections getDataSections] objectAtIndex:section];
    
    NSArray *sectionData = [[CCData sharedData] getDataForSection:sectionString];
    NSDictionary *venue = [sectionData objectAtIndex:row];
    
    self.local = [venue objectForKey:@"Local:"];
    self.name = [venue objectForKey:@"Nome:"];
    self.date = [venue objectForKey:@"Data:"];
    self.enrollment = [venue objectForKey:@"Data final das inscrições:"];
    self.applications = [venue objectForKey:@"Atletas inscritos:"];
    self.distance = [venue objectForKey:@"Distância:"];
    self.description = [venue objectForKey:@"Descrição:"];
    self.link = [venue objectForKey:@"Inscrições (Link):"];
    
    return self;
}

@end
