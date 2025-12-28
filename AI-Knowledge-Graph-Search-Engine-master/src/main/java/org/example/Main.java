package org.example;

import org.example.repository.Neo4jConnection;
import org.example.service.*;
import org.example.model.*;
import org.example.model.enums.*;

/**
 * Main entry point for testing the AI Knowledge Graph Search Engine
 * Run this class in IntelliJ IDEA to test the system
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("🚀 AI Knowledge Graph Search Engine - Starting...\n");

        // Test Neo4j Connection
        if (!testNeo4jConnection()) {
            System.err.println("❌ Neo4j connection failed. Please start Neo4j database.");
            System.err.println("   Expected: bolt://localhost:7687");
            System.err.println("   Username: neo4j");
            System.err.println("   Password: 11111111");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  SYSTEM READY - All Services Initialized");
        System.out.println("=".repeat(60) + "\n");

        // Run demo
        runDemo();

        System.out.println("\n✅ Demo completed successfully!");
        System.out.println("🎉 Your AI Knowledge Graph Search Engine is working!\n");
    }

    private static boolean testNeo4jConnection() {
        System.out.println("📡 Testing Neo4j Connection...");
        try {
            Neo4jConnection connection = Neo4jConnection.getInstance();
            if (connection.getDriver() != null) {
                System.out.println("✅ Neo4j connection successful!");
                return true;
            }
        } catch (Exception e) {
            System.err.println("❌ Neo4j connection error: " + e.getMessage());
        }
        return false;
    }

    private static void runDemo() {
        try {
            // Demo 1: User Service
            System.out.println("\n📋 DEMO 1: User Management");
            System.out.println("-".repeat(60));
            demoUserService();

            // Demo 2: Ticket Service
            System.out.println("\n📋 DEMO 2: Ticket Management");
            System.out.println("-".repeat(60));
            demoTicketService();

            // Demo 3: AI Service
            System.out.println("\n📋 DEMO 3: AI-Powered Features");
            System.out.println("-".repeat(60));
            demoAIService();

            // Demo 4: Analytics Service
            System.out.println("\n📋 DEMO 4: Analytics & Reporting");
            System.out.println("-".repeat(60));
            demoAnalyticsService();

            // Demo 5: Search Service
            System.out.println("\n📋 DEMO 5: Advanced Search");
            System.out.println("-".repeat(60));
            demoSearchService();

        } catch (Exception e) {
            System.err.println("❌ Demo error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demoUserService() {
        UserService userService = new UserService();

        System.out.println("Creating test user...");
        User user = userService.createUser(
                "john_doe",
                "john@example.com",
                "securePass123",
                "John Doe",
                UserRole.AGENT);

        System.out.println("✅ User created:");
        System.out.println("   ID: " + user.getId());
        System.out.println("   Username: " + user.getUsername());
        System.out.println("   Email: " + user.getEmail());
        System.out.println("   Role: " + user.getRole());
    }

    private static void demoTicketService() {
        TicketService ticketService = new TicketService();

        System.out.println("Creating test ticket...");
        Ticket ticket = ticketService.createTicket(
                "Login Issue",
                "Users cannot login to the system",
                "CAT001",
                Priority.HIGH,
                "user123");

        System.out.println("✅ Ticket created:");
        System.out.println("   ID: " + ticket.getId());
        System.out.println("   Title: " + ticket.getTitle());
        System.out.println("   Priority: " + ticket.getPriority());
        System.out.println("   Status: " + ticket.getStatus());

        // Get ticket count
        long count = ticketService.getTicketCount();
        System.out.println("   Total tickets in system: " + count);
    }

    private static void demoAIService() {
        AIService aiService = new AIService();

        System.out.println("Testing AI classification...");

        // Test 1: Classify ticket
        String category1 = aiService.classifyTicket("System crashes when clicking submit button");
        System.out.println("✅ Classification 1: \"System crashes...\" → " + category1);

        String category2 = aiService.classifyTicket("Need dark mode feature");
        System.out.println("✅ Classification 2: \"Need dark mode...\" → " + category2);

        // Test 2: Suggest priority
        Priority priority = aiService.suggestPriority("Database is down, all users affected");
        System.out.println("✅ Priority suggestion: \"Database down...\" → " + priority);

        // Test 3: Extract keywords
        String text = "Login authentication fails with error 500 on production server";
        System.out.println("✅ Keywords extracted from: \"" + text + "\"");
        System.out.println("   Keywords: " + aiService.extractKeywords(text));
    }

    private static void demoAnalyticsService() {
        AnalyticsService analyticsService = new AnalyticsService();

        System.out.println("Generating dashboard metrics...");

        var metrics = analyticsService.getDashboardMetrics();
        System.out.println("✅ Dashboard Metrics:");
        System.out.println("   Total Tickets: " + metrics.get("totalTickets"));
        System.out.println("   Open Tickets: " + metrics.get("openTickets"));
        System.out.println("   Resolved Tickets: " + metrics.get("resolvedTickets"));
        System.out.println("   SLA Compliance: " + metrics.get("slaComplianceRate") + "%");

        var statusCounts = analyticsService.getTicketsByStatus();
        System.out.println("✅ Tickets by Status:");
        statusCounts.forEach((status, count) -> System.out.println("   " + status + ": " + count));
    }

    private static void demoSearchService() {
        SearchService searchService = new SearchService();

        System.out.println("Testing search functionality...");

        // Get search statistics
        var stats = searchService.getSearchStatistics();
        System.out.println("✅ Search Statistics:");
        System.out.println("   Total Tickets: " + stats.get("totalTickets"));
        System.out.println("   Total KB Articles: " + stats.get("totalKBArticles"));
        System.out.println("   Total Users: " + stats.get("totalUsers"));

        // Test search suggestions
        var suggestions = searchService.getSuggestedSearchTerms("bug");
        System.out.println("✅ Search suggestions for 'bug': " + suggestions);
    }
}
